import java.util.*;

class Solution {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    
    public int solution(int[][] board) {
        return bfs(board);
    }
    
    public int bfs(int[][] board) {
        int n = board.length;
        Queue<Robot> queue = new ArrayDeque<>();
        Set<Robot> visited = new HashSet<>();
        
        Robot start = new Robot(new Point(0, 0), new Point(0, 1), 0);
        visited.add(start);
        queue.add(start);
        
        while (!queue.isEmpty()) {
            Robot robot = queue.poll();
            
            if (robot.isAtGoal(n)) {
                return robot.count;
            }
            
            // 4방향 이동
            for (int i = 0; i < 4; i++) {
                Robot next = robot.move(dr[i], dc[i]);
                if (next.isValid(board) && !visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
            
            // 회전
            for (Robot rotated : robot.rotate(board)) {
                if (!visited.contains(rotated)) {
                    visited.add(rotated);
                    queue.add(rotated);
                }
            }
        }
        
        return -1;
    }
}

class Point {
    int row, col;
    
    Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    Point move(int dr, int dc) {
        return new Point(row + dr, col + dc);
    }
    
    boolean isInBounds(int n) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }
    
    boolean isWall(int[][] board) {
        return board[row][col] == 1;
    }
    
    boolean isGoal(int n) {
        return row == n - 1 && col == n - 1;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point p = (Point) o;
        return row == p.row && col == p.col;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

class Robot {
    Point p1, p2;
    int count;
    
    Robot(Point p1, Point p2, int count) {
        // 항상 정렬된 순서로 저장 (일관된 상태 표현)
        if (p1.row < p2.row || (p1.row == p2.row && p1.col < p2.col)) {
            this.p1 = p1;
            this.p2 = p2;
        } else {
            this.p1 = p2;
            this.p2 = p1;
        }
        this.count = count;
    }
    
    boolean isHorizontal() {
        return p1.row == p2.row;
    }
    
    boolean isAtGoal(int n) {
        return p1.isGoal(n) || p2.isGoal(n);
    }
    
    boolean isValid(int[][] board) {
        int n = board.length;
        return p1.isInBounds(n) && p2.isInBounds(n) 
            && !p1.isWall(board) && !p2.isWall(board);
    }
    
    Robot move(int dr, int dc) {
        return new Robot(p1.move(dr, dc), p2.move(dr, dc), count + 1);
    }
    
    List<Robot> rotate(int[][] board) {
        List<Robot> results = new ArrayList<>();
        int[] dirs = {-1, 1};
        
        if (isHorizontal()) {
            for (int d : dirs) {
                Point newRow = new Point(p1.row + d, p1.col);
                if (canRotate(newRow, new Point(p1.row + d, p2.col), board)) {
                    results.add(new Robot(p1, newRow, count + 1));
                    results.add(new Robot(p2, new Point(p2.row + d, p2.col), count + 1));
                }
            }
        } else {
            for (int d : dirs) {
                Point newCol = new Point(p1.row, p1.col + d);
                if (canRotate(newCol, new Point(p2.row, p1.col + d), board)) {
                    results.add(new Robot(p1, newCol, count + 1));
                    results.add(new Robot(p2, new Point(p2.row, p2.col + d), count + 1));
                }
            }
        }
        
        return results;
    }
    
    private boolean canRotate(Point check1, Point check2, int[][] board) {
        int n = board.length;
        return check1.isInBounds(n) && check2.isInBounds(n)
            && !check1.isWall(board) && !check2.isWall(board);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Robot)) return false;
        Robot r = (Robot) o;
        return p1.equals(r.p1) && p2.equals(r.p2);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(p1, p2);
    }
}