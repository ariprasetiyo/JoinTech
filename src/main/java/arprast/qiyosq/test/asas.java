package arprast.qiyosq.test;

public class asas {
    public int solution(int[] a) {
        if (isAesthetically(a)) {
            return 0;
        }

        int aestheticCount = 0;
        for (int j = 0; j < a.length; j++) {
            int[] newA = copyWithoutAnElement(a, j);
            if (isAesthetically(newA)) {
                aestheticCount++;
            }
        }

        if (aestheticCount == 0) {
            return -1;
        } else {
            return aestheticCount;
        }
    }

    private int[] copyWithoutAnElement(int[] arrayInput, int indexOfRemoved) {
        int arrayLength = arrayInput.length;
        int[] newArr = new int[arrayLength - 1];
        int tempK = 0;
        for (int k = 0; k < arrayLength; k++) {
            if (k != indexOfRemoved) {
                newArr[tempK++] = arrayInput[k];
            }
        }
        return newArr;
    }

    private boolean isAesthetically(int[] inputData) {
        int newArrayLenght = inputData.length;
        int flag = 0;
        for (int i = 0; i < newArrayLenght; i++) {
            if (flag == 0) {
                if (inputData[i] < inputData[i + 1]) {
                    flag = 1;
                } else {
                    flag = 2;
                }
            } else {
                if (flag == 1) {
                    if (i % 2 == 1 && inputData[i] > inputData[i - 1]) {
                    } else if (i % 2 == 0 && inputData[i] < inputData[i - 1]) {
                    } else {
                        return false;
                    }
                } else {
                    if (i % 2 == 1 && inputData[i] < inputData[i - 1]) {
                    } else if (i % 2 == 0 && inputData[i] > inputData[i - 1]) {
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        asas solutionObj = new asas();
        int[] a1 = {1, 3, 1, 2};
        int[] a2 = {3, 4, 5, 3, 7};
        int[] a3 = {1, 2, 4, 4, 2, 5};
        System.out.println(solutionObj.solution(a1));
        System.out.println(solutionObj.solution(a2));
        System.out.println(solutionObj.solution(a3));
    }
}
