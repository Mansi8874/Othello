import java.util.Scanner;

public class Runner {

	static Scanner s = new Scanner(System.in);

	final static int player1Symbol = 1;
	final static int player2Symbol = 2;

	public static void main(String[] args) {
		OthelloBoard b = new OthelloBoard();
		int n = s.nextInt();
		boolean p1Turn = true;
		while(n > 0) {
			int x = s.nextInt();
			int y = s.nextInt();
			boolean ans = false;
			if(p1Turn) {
				ans = b.move(player1Symbol, x, y);
			}
			else {
				ans = b.move(player2Symbol, x, y);
			}
			if(ans) {
				b.print();
				p1Turn = !p1Turn;
				n--;
			}
			else {
				System.out.println(ans);
			}
		}
	}
}
class OthelloBoard {
    private int board[][];
    final static int Player1Symbol = 1;
    final static int Player2Symbol = 2;

    public OthelloBoard() {
        board = new int[8][8];
        board[3][3] = Player1Symbol;
        board[3][4] = Player2Symbol;
        board[4][3] = Player2Symbol;
        board[4][4] = Player1Symbol;
    }

    public void print() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    int xdir[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
    int ydir[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

    public boolean move(int symbol, int x, int y) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8 || board[x][y] != 0) {
            return false;
        }
        boolean movePossible = false;
        for (int i = 0; i < xdir.length; i++) {
            int xstep = xdir[i];
            int ystep = ydir[i];
            int currentx = x + xstep;
            int currenty = y + ystep;
            int count = 0;
            while (currentx >= 0 && currentx < 8 && currenty >= 0 && currenty < 8) {
                if (board[currentx][currenty] == 0) {
                    break;
                } else if (board[currentx][currenty] != symbol) {
                    currentx += xstep;
                    currenty += ystep;
                    count++;
                } else {
                    if (count > 0) {
                        movePossible = true;
                        int convertx = currentx - xstep;
                        int converty = currenty - ystep;
                        while (convertx != x || converty != y) {
                            board[convertx][converty] = symbol;
                            convertx = convertx - xstep;
                            converty = converty - ystep;
                        }
                    }
                    break;
                }
            }
        }
        if (movePossible) {
            board[x][y] = symbol;
        }
        return movePossible;
    }
}