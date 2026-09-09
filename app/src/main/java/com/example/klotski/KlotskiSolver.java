package com.example.klotski;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 华容道（Klotski）求解器
 * 使用广度优先搜索（BFS）算法求解华容道难题
 */
public class KlotskiSolver {

    private static final int BOARD_WIDTH = 4;
    private static final int BOARD_HEIGHT = 5;
    private static final int EMPTY = 0;

    private int[][] initialState;
    private int[][] targetState;

    public KlotskiSolver() {
        // 初始状态（示例）
        this.initialState = new int[][] {
            {1, 2, 2, 3},
            {1, 2, 2, 3},
            {4, 5, 6, 7},
            {4, 8, 9, 7},
            {10, 10, 0, 11}
        };

        // 目标状态
        this.targetState = new int[][] {
            {1, 1, 0, 0},
            {1, 1, 0, 0},
            {2, 2, 3, 3},
            {2, 2, 3, 3},
            {4, 5, 6, 7}
        };
    }

    /**
     * 求解华容道
     */
    public List<String> solve() {
        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        String initialKey = boardToString(initialState);
        String targetKey = boardToString(targetState);

        if (initialKey.equals(targetKey)) {
            return new ArrayList<>();
        }

        visited.add(initialKey);
        queue.add(new Node(initialState, new ArrayList<>()));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // 找到所有可能的移动
            List<int[][]> nextStates = getNextStates(current.state);

            for (int[][] nextState : nextStates) {
                String nextKey = boardToString(nextState);

                if (nextKey.equals(targetKey)) {
                    List<String> result = new ArrayList<>(current.moves);
                    result.add(stateToString(nextState));
                    return result;
                }

                if (!visited.contains(nextKey)) {
                    visited.add(nextKey);
                    List<String> newMoves = new ArrayList<>(current.moves);
                    newMoves.add(stateToString(nextState));
                    queue.add(new Node(nextState, newMoves));
                }
            }
        }

        return null; // 无解
    }

    /**
     * 获取所有可能的下一状态
     */
    private List<int[][]> getNextStates(int[][] state) {
        List<int[][]> nextStates = new ArrayList<>();

        // 找到空白位置
        int emptyRow = -1, emptyCol = -1;
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (state[i][j] == EMPTY) {
                    emptyRow = i;
                    emptyCol = j;
                    break;
                }
            }
            if (emptyRow != -1) break;
        }

        // 尝试所有可能的移动
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int newRow = emptyRow + dir[0];
            int newCol = emptyCol + dir[1];

            if (isValid(newRow, newCol)) {
                int[][] newState = copyState(state);
                swap(newState, emptyRow, emptyCol, newRow, newCol);
                nextStates.add(newState);
            }
        }

        return nextStates;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < BOARD_HEIGHT && col >= 0 && col < BOARD_WIDTH;
    }

    private int[][] copyState(int[][] state) {
        int[][] copy = new int[BOARD_HEIGHT][BOARD_WIDTH];
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                copy[i][j] = state[i][j];
            }
        }
        return copy;
    }

    private void swap(int[][] state, int r1, int c1, int r2, int c2) {
        int temp = state[r1][c1];
        state[r1][c1] = state[r2][c2];
        state[r2][c2] = temp;
    }

    private String boardToString(int[][] state) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                sb.append(state[i][j]).append(",");
            }
        }
        return sb.toString();
    }

    private String stateToString(int[][] state) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                sb.append(state[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 内部类：搜索树节点
     */
    private static class Node {
        int[][] state;
        List<String> moves;

        Node(int[][] state, List<String> moves) {
            this.state = state;
            this.moves = moves;
        }
    }
}
