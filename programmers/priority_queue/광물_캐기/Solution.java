import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class MineralsInfo {
    int[] minerals;
    int value;

    public MineralsInfo(int[] minerals, int value) {
        this.minerals = minerals;
        this.value = value;
    }
}

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int[][] fatigue = {
                {1, 1, 1},
                {5, 1, 1},
                {25, 5, 1}
        };

        int answer = 0;
        PriorityQueue<MineralsInfo> mineralQueue = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);
        List<String[]> sliceMinerals = new ArrayList<>();
        List<Integer> pickInt = new ArrayList<>();

        int pickCount = 0;
        for (int i = 0; i < picks.length; i++) {
            pickCount += picks[i];
            for (int j = 0; j < picks[i]; j++) {
                pickInt.add(i);
            }
        }

        int chunk = 5;
        for (int i = 0; i < minerals.length; i += chunk) {
            String[] sliceArray = Arrays.copyOfRange(minerals, i, Math.min(i + chunk, minerals.length));
            sliceMinerals.add(sliceArray);
        }

        int maxCount = Math.min(pickCount, sliceMinerals.size());
        for (int i = 0; i < maxCount; i++) {
            String[] mineralsPart = sliceMinerals.get(i);
            int[] mineralInts = getMineralInts(mineralsPart);
            int value = getValue(mineralsPart);
            mineralQueue.add(new MineralsInfo(mineralInts, value));
        }

        for (int i = 0; i < maxCount; i++) {
            MineralsInfo info = mineralQueue.poll();
            int[] mineralInt = info.minerals;
            for (int m : mineralInt) {
                answer += fatigue[pickInt.get(i)][m];
            }
        }

        return answer;
    }

    private int getValue(String[] minerals) {
        int value = 0;

        for (String mineral : minerals) {
            if (mineral.equals("diamond")) value += 25;
            else if (mineral.equals("iron")) value += 5;
            else value += 1;
        }

        return value;
    }

    private int[] getMineralInts(String[] minerals) {
        int[] result = new int[minerals.length];

        for (int i = 0; i < minerals.length; i++) {
            if (minerals[i].equals("diamond")) result[i] = 0;
            else if (minerals[i].equals("iron")) result[i] = 1;
            else result[i] = 2;
        }

        return result;
    }
}
