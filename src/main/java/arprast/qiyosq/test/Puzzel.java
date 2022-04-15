package arprast.qiyosq.test;

import java.util.ArrayList;
import java.util.List;

public class Puzzel {

    public static void main(String[] args) {
        int number = 670;
        System.out.println(solution(number));
        System.out.println(solution(268));
        System.out.println(solution(50));
        System.out.println(solution(-999));
    }

    public static int solution(int N) {
        int value = 0;
        int resultFirst, resultSecond, resultThird, resultFourth = 0;
        int remainFirst, remainSecond, remainThird = 0;
        int arrNumb = 0;
        List<Integer> list = new ArrayList<>();
        boolean isMinus = false;
        if (N < 0) {
            value = N;
            N = -N;
            isMinus = true;
        } else {
            value = N;
        }

        if (N == 0) {
            list.add(0, 5);
            list.add(0);
        }

        if (N > 0) {
            if (N > 1000) {
                resultFirst = N / 1000;
                remainFirst = N % 1000;
                list.add(resultFirst);
                if (remainFirst > 0) {
                    N = remainFirst;
                } else {
                    N = 0;
                    resultSecond = 0;
                    resultThird = 0;
                    resultFourth = 0;
                    list.add(resultSecond);
                    list.add(resultThird);
                    list.add(resultFourth);
                }
            }

            if (N > 100) {
                resultSecond = N / 100;
                remainSecond = N % 100;
                list.add(resultSecond);
                if (remainSecond > 0) {
                    N = remainSecond;
                } else {
                    resultThird = 0;
                    resultFourth = 0;
                    N = 0;
                    list.add(resultThird);
                    list.add(resultFourth);
                }
            }

            if (N > 10) {
                resultThird = N / 10;
                remainThird = N % 10;
                list.add(resultThird);
                resultFourth = remainThird;
                list.add(resultFourth);

            }

            if (value > 0) {
                int j = 0;
                for (j = 0; j < list.size(); j++) {
                    arrNumb = Math.abs(list.get(j));
                    if (arrNumb < 5) {
                        list.add(j, 5);
                        break;
                    }
                    j++;
                }

                if (arrNumb > 5) {
                    list.add(5);
                }
            } else {

                int j = 0;
                for (j = 0; j < list.size(); j++) {
                    arrNumb = Math.abs(list.get(j));
                    if (arrNumb > 5) {
                        list.add(j, 5);
                        break;
                    }
                    j++;
                }

                if (arrNumb < 5) {
                    list.add(5);
                }
            }
        }

        final StringBuilder collect = new StringBuilder();
        for(final int numberOfVal : list){
            collect.append(numberOfVal);
        }


        return (isMinus == true ? Integer.valueOf(collect.toString()) * -1  : Integer.valueOf(collect.toString()) );

    }
}
