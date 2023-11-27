import java.util.LinkedList;
import java.util.Queue;

/**
 * 첫번째 방법
 * 방문 처리를 poll 한 시점에서 하는 방법
 * 같은 위치 값에 대한 경로 길이 값이 다르면 탐색할 수 없음
 * 반례 [[0,0,0,0,0,0],[0,1,1,1,1,0],[0,0,1,0,0,0],[1,0,0,1,0,1],[0,1,0,0,0,1],[0,0,0,0,0,0]]
 * 실패
 */

/**
 * 두번째 방법
 * memoization을 통한 cost dp 방법
 * 특정 시점에 임의의 경로에 대한 특정 위치의 cost 값이 낮아도 다음 위치에 대한 cost 값은 다른 경로가 더 낮을 수 있음
 * 반례 [[0, 0, 0, 0, 0],[0, 1, 1, 1, 0],[0, 0, 1, 0, 0],[1, 0, 0, 0, 1],[1, 1, 1, 0, 0]]
 * 실패
 */

/**
 * 세번째 방법
 * memoization + direction 속성을 이용한 방문 처리 및 dp 방법
 */

class Cell {
    int x;
    int y;
    int cost;
    int[] direction;

    public Cell(int x, int y, int cost, int[] direction) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.direction = direction;
    }
}

class Solution {
    public int solution(int[][] board) {
        return bfs(board);
    }

    int bfs(int[][] board) {
        Queue<Cell> queue = new LinkedList<>();
        int[][] memo = new int[board.length][board.length];
        boolean[][][] visited = new boolean[board.length][board.length][4];

        int result = Integer.MAX_VALUE;

        // direction
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, -0};

        // queue init
        Cell start = new Cell(0, 0, 0, new int[]{0, 0});
        queue.add(start);

        while (!queue.isEmpty()) {
            Cell cell = queue.poll();

            // 목적지 도달 체크
            if (cell.x == board.length - 1 && cell.y == board.length - 1) {
                result = Math.min(result, cell.cost);
                continue;
            }

            for (int index = 0; index < 4; index++) {
                // 좌표 계산
                int x = cell.x + dx[index];
                int y = cell.y + dy[index];

                // 범위 및 벽 검사
                if (x < 0 || y < 0 || x >= board.length || y >= board.length || board[x][y] == 1) {
                    continue;
                }

                // cost 계산
                int cost = cell.cost + 100;
                if (cell.direction[0] != dx[index] && cell.direction[1] != dy[index]) {
                    cost += 500;
                }

                // memoization
                if (!visited[x][y][index] || memo[x][y] >= cost) {
                    visited[x][y][index] = true;
                    memo[x][y] = cost;
                    queue.add(new Cell(x, y, memo[x][y], new int[]{dx[index], dy[index]}));
                }
            }
        }

        return result;
    }
}


