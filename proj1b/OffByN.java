public class OffByN implements CharacterComparator {
    private int diff;
    OffByN(int N){
        this.diff = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        int different = (int) x - (int) y;
        return Math.abs(different) == diff;
    }
}
