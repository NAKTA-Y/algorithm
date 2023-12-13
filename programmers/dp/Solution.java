class Solution {
    public int solution(String s) {
        int answer = 1;

        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j > i; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    String subString = s.substring(i, j + 1);
                    if (isPalindrome(subString)) {
                        answer = Math.max(answer, subString.length());
                        break;
                    }
                }
            }
        }
        return answer;
    }

    private boolean isPalindrome(String s) {
        for (int i = 0; i < (s.length() / 2); i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
