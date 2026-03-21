public interface IUserInterfaceContract {
    interface EventListner {
        void onSudokuInput(int x, int y, int input);
        void onDailogClick();
    }

    interface View {
        void setListner(IUserInterfaceContract.EventListner listner);
        void updateSquare(int x, int y, int input);
        void updateBoard(SudokuGame game);
        void showDialog(String Message);
        void showError(String message);
    }
}
