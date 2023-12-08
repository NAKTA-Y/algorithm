class Node {
    int x;
    int y;
    int[] currentDirection;
    int count;

    public Node(int x, int y, int[] currentDirection, int count) {
        this.x = x;
        this.y = y;
        this.currentDirection = currentDirection;
        this.count = count;
    }
}

class Solution {
    public int solution(String[] board) {
        int answer = -1;
        Queue<Node> bfsQueue = new LinkedList<>();
        boolean[][] visited = new boolean[board.length][board[0].length()];
        int[] startPosition = new int[2];
        int[] xDirection = {0, 0, 1, -1};
        int[] yDirection = {1, -1, 0, 0};

        // find start position
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'R') {
                    startPosition[0] = i;
                    startPosition[1] = j;
                }
            }
        }

        // BFS
        bfsQueue.add(new Node(startPosition[0], startPosition[1], new int[]{0, 0}, 0));

        while (!bfsQueue.isEmpty()) {
            // 미끄러져 이동
            Node peekNode = bfsQueue.peek();
            if (peekNode.currentDirection[0] != peekNode.currentDirection[1]
                    && isSafePath(peekNode.x + peekNode.currentDirection[0], peekNode.y + peekNode.currentDirection[1], board)) {
                peekNode.x += peekNode.currentDirection[0];
                peekNode.y += peekNode.currentDirection[1];
                continue;
            }

            Node node = bfsQueue.poll();

            // 목표 도달 체크
            if (board[node.x].charAt(node.y) == 'G') {
                answer = node.count;
                break;
            }

            if (visited[node.x][node.y]) continue;
            visited[node.x][node.y] = true;

            // 방향 전환
            for (int i = 0; i < 4; i++) {
                int x = node.x + xDirection[i];
                int y = node.y + yDirection[i];

                if (!isSafePath(x, y, board)) {
                    continue;
                }

                bfsQueue.add(new Node(x, y, new int[]{xDirection[i], yDirection[i]}, node.count + 1));
            }
        }

        return answer;
    }

    private boolean isSafePath(int x, int y, String[] board) {
        return x >= 0 && x < board.length && y >= 0 && y < board[x].length() && board[x].charAt(y) != 'D';
    }
}