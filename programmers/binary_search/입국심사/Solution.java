class Solution {
    public long solution(int n, int[] times) {
        long answer = 1;
        Arrays.sort(times);
        long min = 1;
        long max = (long) times[0] * n;

        while (min <= max) {
            long mid = (min + max) / 2;
            long temp = 0;

            for (int i = 0; i < times.length; i++) {
                temp += mid / times[i];
            }

            if (temp < n) {
                min = mid + 1;
            } else {
                answer = mid;
                max = mid - 1;
            }
        }


        return answer;
    }
}