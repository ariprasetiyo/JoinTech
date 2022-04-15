package arprast.qiyosq.test;

public class CyclicRotation {

        public static void main(String[] args) {
            int[] input = {1, 3, 4, 8, 9, 10, 11, 6};
            solution(input, 6);
        }

        public static int[] solution(int[] A, int K) {
            if (A.length == K || A.length <= 1) {
                return A;
            }

            final int size = A.length;
            int[] result = new int[size];
            K %= size; //modulo for taking into account the case when A.length < K
            for (int i = 0; i < size - K; ++i) {
                result[K + i] = A[i];
            }

            for (int i = 0; i < K; ++i) {
                result[i] = A[size - K + i];
            }
            return result;
        }

}
