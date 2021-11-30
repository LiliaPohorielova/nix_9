package ua.com.alevel.util;

import ua.com.alevel.date.CustomDate;

public class StringToDate {

    public static CustomDate stringToDate(String inputString) {
        String[] splitString = inputString.split(" ");
        int nums[] = new int[7];
        for (int i = 0; i < 7; i++) {
            nums[i] = Integer.parseInt(splitString[i]);
        }
        //TODO: ПЕРЕДЕЛАТЬ! + ДОБАВИТЬ ПРОВЕРКУ ВАЛИДНОСТИ
        CustomDate date = new CustomDate(nums[0],nums[1],nums[2],nums[3],nums[4],nums[5],nums[6]);
        return date;
    }
}
