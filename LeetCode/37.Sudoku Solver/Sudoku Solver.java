// Problem: 37. Sudoku Solver (Hard)
// Link: https://leetcode.com/problems/sudoku-solver

class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c; // 숫자 배치

                            if (solve(board)) {
                                return true;
                            }

                            board[i][j] = '.'; 
                        }
                    }
                    return false; 
                }
            }
        }
        return true; 
    }

    /**
     * board: 보드
     * row: 현재 행
     * col: 현재 열
     * c: 넣으려는 문자 ('1'~'9')
     */
    public boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) {
                return false;
            }
        }

        if (row < 3 && col < 3) { // 1번 구역
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == c) return false;
                }
            }
        } else if (row < 3 && col < 6) { // 2번 구역
            for (int i = 0; i < 3; i++) {
                for (int j = 3; j < 6; j++) {
                    if (board[i][j] == c) return false;
                }
            }
        } else if (row < 3 && col < 9) { // 3번 구역
            for (int i = 0; i < 3; i++) {
                for (int j = 6; j < 9; j++) {
                    if (board[i][j] == c) return false;
                }
            }
        }

        else if (row < 6 && col < 3) { // 4번 구역
            for (int i = 3; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == c) return false;
                }
            }
        } else if (row < 6 && col < 6) { // 5번 구역
            for (int i = 3; i < 6; i++) {
                for (int j = 3; j < 6; j++) {
                    if (board[i][j] == c) return false;
                }
            }
        } else if (row < 6 && col < 9) { // 6번 구역
            for (int i = 3; i < 6; i++) {
                for (int j = 6; j < 9; j++) {
                    if (board[i][j] == c) return false;
                }
            }
        }

        else if (row < 9 && col < 3) { // 7번 구역
            for (int i = 6; i < 9; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == c) return false;
                }
            }
        } else if (row < 9 && col < 6) { // 8번 구역
            for (int i = 6; i < 9; i++) {
                for (int j = 3; j < 6; j++) {
                    if (board[i][j] == c) return false;
                }
            }
        } else if (row < 9 && col < 9) { // 9번 구역
            for (int i = 6; i < 9; i++) {
                for (int j = 6; j < 9; j++) {
                    if (board[i][j] == c) return false;
                }
            }
        }

        return true;
    }
}