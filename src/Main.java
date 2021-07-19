import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileReader;


public class Main {
    private static String bin;

    private static int bi(int num) {
        return bin.charAt(num) - '0';
    }
    public static void main(String[] arg) throws IOException {
        int test_case;


        BufferedWriter ou = new BufferedWriter(new OutputStreamWriter(System.out));
//        File txt = new File("sample_input.txt");
//        try {
//            BufferedReader txt_Scan = new BufferedReader(new FileReader(txt));
//            N = Integer.parseInt(txt_Scan.readLine());   // 첫번째 줄.
//
//
//        } catch (Exception e) {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        test_case = Integer.parseInt(in.readLine());   // 테스트 수
        int i;
        StringTokenizer st;
        for(i = 0; i < test_case; i++){
            st = new StringTokenizer(in.readLine(), " ");

            int N = Integer.parseInt(st.nextToken());   // 비트수
            int T = Integer.parseInt(st.nextToken());   // 자연수

            bin = in.readLine();
            int[] a_arr = new int[N];
            int num;
            for(num = 0; num < N; num++){
                a_arr[num] = -1;
            }
            for(num = N - T - T; num < N - T; num++) {  // 왼쪽에서 오른쪽으로 채움 red
                a_arr[num] = bi(num + T);
            }

            for(num = 0; num < T; num++) {   //  blue
                a_arr[num + T] = bi(num);
            }
            num = 0;
            while(a_arr[num + T * 2] <= N){
                if(a_arr[num] != -1){                                         // 현재 A 값이 이미 정의 된 경우
                    if (a_arr[num] == 1 && bi(num + T) == 1){            // 1인경우
                        if(a_arr[num + T * 2] == -1) {
                            a_arr[num + T * 2] = 0;
                        }
                    } else if (a_arr[num] == 0 && bi(num + T) == 1) {    // 0인경우 대응하는 B 숫자 1인 경우
                        if (a_arr[num + T * 2] == -1) {
                            a_arr[num + T * 2] = 1;
                        }
                    } else if (a_arr[num] == 0 && bi(num + T) == 0) {    // 0인경우 대응하는 B 숫자 0인 경우
                        if (a_arr[num + T * 2] == -1) {
                            a_arr[num + T * 2] = 0;
                        }
                    }

                } else {    // 정의 안된경우
                    if(bi(num + T) == 1) {          // 대응하는 B 숫자가 1인 경우
                        if(a_arr[num + T * 2] == 0){   // 다음 a가 0일 때
                            a_arr[num] = 1;
                        } else {
                            a_arr[num] = 0;             // 1또는 정의가 안되어있을 때
                            a_arr[num + T * 2] = 1;
                        }
                    } else if(bi(num + T) == 0){    // 대응하는 B 숫자가 0인 경우
                        a_arr[num] = 0;                 // 1또는 정의가 안되어있을 때
                        a_arr[num + T * 2] = 0;
                    }
                }

                num++;
            }


            for(num = 0; num < N; num++){
                System.out.print(a_arr[num]);
            }

            ou.write("\n");
        }

        ou.flush();
        ou.close();
    }


}