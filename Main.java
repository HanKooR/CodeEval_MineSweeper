package codeeval_FindMines;
/*
The MIT License (MIT)

Copyright (c) 2015 HanKooR

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
/*
 Challenge Description:
 You will be given an M*N matrix. Each item in this matrix is either a '*' or a
 '.'. A '*' indicates a mine whereas a '.' does not. The objective of the 
 challenge is to output a M*N matrix where each element contains a number 
 (except the positions which actually contain a mine which will remain as '*') 
 which indicates the number of mines adjacent to it. Notice that each position 
 has at most 8 adjacent positions e.g. left, top left, top, top right, right, ... 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static void loadFile(String fileName) {
        File file = new File(fileName);
        if (!file.canRead() || !file.isFile()) {
            System.exit(0);
        }
        BufferedReader in = null;
        String row = null;
        int rowsCnt, clmCnt;
        try {
            in = new BufferedReader(new FileReader(fileName));
            while ((row = in.readLine()) != null) {
                clmCnt = Integer.parseInt(row.split(";")[0].split(",")[0]);
                rowsCnt = Integer.parseInt(row.split(";")[0].split(",")[1]);
                char[][] minefield = new char[clmCnt][rowsCnt];
                String minestring = row.split(";")[1];
                int cnt = 0;
                for (int i = 0; i < minefield.length; i++) {
                    for (int j = 0; j < minefield[0].length; j++) {
                        minefield[i][j] = minestring.charAt(cnt);
                        cnt++;
                    }
                }
                minefield = findMines(minefield);
                for (int i = 0; i < minefield.length; i++) {
                    for (int j = 0; j < minefield[0].length; j++) {
                        System.out.print(minefield[i][j]);
                    }
                }
                System.out.println();
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ioEx) {
                    ioEx.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] pathToFile) {
        if ((pathToFile != null) && (pathToFile.length == 1) && (!pathToFile[0].isEmpty())) {
            loadFile(pathToFile[0]);
        }
    }

    public static char[][] findMines(char[][] minefield) {
        for (int i = 0; i < minefield.length; i++) {
            for (int j = 0; j < minefield[0].length; j++) {
                int mineCoutner = 0;
                if (minefield[i][j] == '*') {
                    //do nothing
                } else {
                    //check left side
                    if (j > 0) {
                        //left
                        if (minefield[i][j - 1] == '*') {
                            mineCoutner++;
                        }
                        //left upper
                        if (i > 0) {
                            if (minefield[i - 1][j - 1] == '*') {
                                mineCoutner++;
                            }
                        }
                        //left lower
                        if (i < minefield.length - 1) {
                            if (minefield[i + 1][j - 1] == '*') {
                                mineCoutner++;
                            }
                        }
                    }
                    //check right side
                    if (j < minefield[i].length - 1) {
                        //right
                        if (minefield[i][j + 1] == '*') {
                            mineCoutner++;
                        }
                        //right lower
                        if (i < minefield.length - 1) {
                            if (minefield[i + 1][j + 1] == '*') {
                                mineCoutner++;
                            }
                        }
                        if (i > 0) {
                            if (minefield[i - 1][j + 1] == '*') {
                                mineCoutner++;
                            }
                        }
                    }
                    //check up/down
                    if (i > 0) {
                        if (minefield[i - 1][j] == '*') {
                            mineCoutner++;
                        }
                    }
                    if (i < minefield.length - 1) {
                        if (minefield[i + 1][j] == '*') {
                            mineCoutner++;
                        }
                    }
                    minefield[i][j] = String.valueOf(mineCoutner).charAt(0);
                }
            }

        }
        return minefield;
    }
}