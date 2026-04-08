package org.example;

public class PalindromeCheck {

    public static void main(String[] args) {

        String str = "abba";
        boolean res = checkPalindrome(str);
        if(res){
            System.out.println(str+" is a palindrome");
        } else{
            System.out.println(str+" is not a palindrome");
        }
    }

    private static boolean checkPalindrome(String str) {
        int left = 0;
        int right = str.length()-1;

        while(left < right){
            if(str.charAt(left) == str.charAt(right)){
                left++;
                right--;
            }
            else {
                return false;
            }
        }
        return true;
    }
}
