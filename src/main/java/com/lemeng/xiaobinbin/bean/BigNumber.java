package com.lemeng.xiaobinbin.bean;

import java.util.ArrayList;

import com.lemeng.xiaobinbin.MyDebug;

public class BigNumber{
    public ArrayList<BigNumberUnit> mList = new ArrayList<BigNumberUnit>();

    public static BigNumber getBigNumForString(String s) {
        if (s == null || s.length() == 0) {
            return new BigNumber();
        }
        if (s.contains("-")) {
            BigNumber big3 = new BigNumber();
            BigNumberUnit unit3 = new BigNumberUnit();
            unit3.value = 0;
            big3.mList.add(unit3);
            return new BigNumber();
        }
        if (s.contains("E+") || s.contains("E") ) {
            s = s.replace("E+", "E");
            String[] str1 = s.split("E");
            int count = Integer.parseInt(str1[1]);
            MyDebug.log2("===================================str1[0] = "+str1[0]);
            if(str1[0].contains(".")) {
                String str3 = "";
                String[] str2 = str1[0].split("\\.");
                for(String sss : str2) {
                	MyDebug.log2("===================================sss = "+sss);
                }
                if (str2.length == 1)
                {
                    for (int i = 0; i < count; i++)
                    {
                        str3 += "0";
                    }
                    s = str2[0] + str3;
                }
                else if (str2.length == 2) {
                    count = count - str2[1].length();
                    for (int i = 0; i < count; i++)
                    {
                        str3 += "0";
                    }
                    s = str2[0] + str2[1] + str3;
                }
            	
            }else {
            	String str3 = "";
                for (int i = 0; i < count; i++)
                {
                    str3 += "0";
                }
                s = str1[0] + str3;
            }
        }
        MyDebug.log2("===================================getBigNumForString s= "+s);
        BigNumber big = new BigNumber();
        int index = 0;
        while (s.length() >= 4) {
            BigNumberUnit unit = new BigNumberUnit();
            s = spileNumberString(s, unit);
            index++;
            big.mList.add(unit);
        }
        BigNumberUnit unit1 = new BigNumberUnit();
        unit1.value =Integer.parseInt(s);
        big.mList.add(unit1);
        return big;
    }

    public boolean isEmpty() {
        if (mList.size() == 0)
        {
            return true;
        }
        else {
            return false;
        }

    }

    private static String spileNumberString(String str, BigNumberUnit unit) {
        String s1 = str.substring(str.length() - 3, str.length());
    //    MyDebug.log2("spileNumberString s="+s1);
        unit.value =Integer.parseInt(s1);
        str = str.substring(0, str.length() - 3);
      //  MyDebug.log2("spileNumberString str="+str);
        return str;
    }
    public String toString() {
        String str = "";
        for (int i = mList.size()-1; i >= 0; i--) {
            if (i == mList.size() - 1) {
                str += mList.get(i).value;
                continue;
            }
            if (mList.get(i).value > 99)
            {
                str += mList.get(i).value;
            }
            else if (mList.get(i).value > 9)
            {
                str = str + "0" + mList.get(i).value;
            }
            else if (mList.get(i).value > 0)
            {
                str = str + "00" + mList.get(i).value;
            }
            else {
                str = str + "000";
            }
        }
        return str;
    }

    public static BigNumber add(BigNumber big1, BigNumber big2) {
        BigNumber min, max;
        int minLeng, maxLeng;
//        Debug.Log("BigNumber add bg1= " + big1.toString() + " bg2 = " + big2.toString());
//        Debug.Log("BigNumber add bg1= " + big1.toStringWithUnit() + " bg2 = " + big2.toStringWithUnit());
        if (big1.mList.size() > big2.mList.size())
        {
            max = big1;
            min = big2;
            minLeng = min.mList.size();
            maxLeng = max.mList.size();
        }
        else {
            max = big2;
            min = big1;
            minLeng = min.mList.size();
            maxLeng = max.mList.size();
        }
        BigNumber back = new BigNumber();
        int index = 0;
        int up = 0;
        while (index < maxLeng) {
            BigNumberUnit unit1 = new BigNumberUnit();
            if (index < minLeng)
            {
                int value = max.mList.get(index).value + min.mList.get(index).value + up;
                unit1.value = value % 1000;
                up = value / 1000;
                back.mList.add(unit1);
            }
            else {
                int value = max.mList.get(index).value + up;
                unit1.value = value % 1000;
                up = value / 1000;
                back.mList.add(unit1);
            }
            index++;
        }
        if(up != 0) {
            BigNumberUnit unit1 = new BigNumberUnit();
            unit1.value = up;
            back.mList.add(unit1);
        }
        return back;
    }
    public static BigNumber minus(BigNumber big1, BigNumber big2) {
        int isEquit = big1.ieEquit(big2);
        BigNumber back = new BigNumber();
        if (isEquit == 0)
        {
            BigNumberUnit unit1 = new BigNumberUnit();
            back.mList.add(unit1);
        }
        else  {
            back = minusZheng(big1,big2);
        }
        return back;
    }

    public static  BigNumber multiply(BigNumber big, float multiplying) {
        int index = 0;
        int leng = big.mList.size();
        int up = 0;
        BigNumberUnit unit1;
        int value;
        BigNumber back = new BigNumber();
        while (index < leng) {
            unit1 = new BigNumberUnit();
            float v = big.mList.get(index).value * multiplying;
//            Debug.Log("big.mList.get(index).value =" + big.mList.get(index).value + " multiplying = " + multiplying + " v=" + v);
            int v2 = (int)v;
//            Debug.Log("v2 =" + v2);
            float v3 = v - v2;
//            Debug.Log("v3 =" + v3);

            if (index > 0 && v3 > 0) {
                int v4 = (int)(v3 * 1000);
                int v5 = back.mList.get(index-1).value + v4;
//                Debug.Log("v5 =" + v5 );
                if (v5 >= 1000)
                {
                    back.mList.get(index-1).value = v5 % 1000;
                    up = up + v5 / 1000;
                }
                else {
                    back.mList.get(index-1).value = v5;
                }
//                Debug.Log("big.mList.get(index-1).value =" + big.mList.get(index-1).value);
            }

            value = (int)(big.mList.get(index).value * multiplying)+up;
           
            unit1.value = value % 1000;
//            Debug.Log("value =" + unit1.value+" unit = "+ unit1.unit);
            back.mList.add(unit1);
            up = value / 1000;
            index++;
        }
        if (up != 0) {
            unit1 = new BigNumberUnit();
            unit1.value = up;
//            Debug.Log("value =" + unit1.value + " unit = " + unit1.unit);
            back.mList.add(unit1);
        }
        return back;
    }

    private static BigNumber minusZheng(BigNumber max, BigNumber min) {
        BigNumber big = new  BigNumber();
        int minLeng, maxLeng;
        int index = 0;
        int up = 0;
        minLeng = min.mList.size();
        maxLeng = max.mList.size();
        while (index < maxLeng)
        {
            BigNumberUnit unit1 = new BigNumberUnit();
            if (index < minLeng)
            {
                int value;
                if (max.mList.get(index).value + up < min.mList.get(index).value)
                {
                    value = 1000 + max.mList.get(index).value + up - min.mList.get(index).value;
                    up = -1;
                }
                else {
                    value =  max.mList.get(index).value + up - min.mList.get(index).value;
                    up = 0;
                }
                unit1.value = value;
            }
            else if(index < maxLeng)
            {
                int value = max.mList.get(index).value + up;
                if (value < 0)
                {
                    value = 1000 + value;
                    up = -1;
                }
                else {
                    up = 0;
                }
                unit1.value = value;               
            }
            big.mList.add(unit1);
            index++;
        }
        BigNumberUnit unit2 = big.mList.get(big.mList.size() - 1)	;
        if (unit2.value == 0) {
            big.mList.remove(unit2);
        }
        return big;
    }

    public int ieEquit(BigNumber big) {
        if (big.mList.size() > mList.size())
        {
            return -1;
        }
        else if (big.mList.size() < mList.size()) {
            return 1;
        }else{
            for (int i = big.mList.size() - 1; i >= 0; i--) {
                if (big.mList.get(i).value > mList.get(i).value)
                {
                    return -1;
                }
                else if (big.mList.get(i).value < mList.get(i).value)
                {
                    return 1;
                }
            }


            return 0;
        }
    }
}
