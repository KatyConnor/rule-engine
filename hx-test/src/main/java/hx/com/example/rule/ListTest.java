package hx.com.example.rule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author mingliang
 * @Date 2018-04-08 11:30
 */
public class ListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i =0; i< 10050; i++){
            list.add("count = "+i);
        }

        int num = ( list.size() % 1000 == 0 ) ? ( list.size() / 1000 ) : ( (list.size() / 1000) + 1 );
//        System.out.println(1001 / 10);
//        System.out.println(num);
        if ( num == 1 ) {
            System.out.println(list.subList(0,list.size()).get(0));
        } else {
            List<String>  temp =  new ArrayList<>();
            for (int i =0; i< num; i++){
                temp.addAll(list.subList(i*1000,(i+1)*1000>list.size()?list.size():(i+1)*1000));
//                System.out.println(list.subList(i*1000,(i+1)*1000>list.size()?list.size():(i+1)*1000).get(0)+"----"+list.subList(i*1000,(i+1)*1000>list.size()?list.size():(i+1)*1000).get(list.subList(i*1000,(i+1)*1000>list.size()?list.size():(i+1)*1000).size()-1));
                System.out.println(temp.get(0)+"----"+temp.get(temp.size()-1));
                temp.clear();
            }
        }
//        int number = ( 7017 % 1000 == 0 ) ? ( 7017 / 1000 ) : ( (7017 / 1000) + 1 );
//        System.out.println(number);
        try {
            int result = between(new Date(),new Date());
            System.out.println("result = "+result);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    /**
     * 计算两个时间的差， date2 - date1
     * @param date1 <Date>
     * @param date2 <Date>
     * @return int
     * @throws ParseException
     */
    public static int between(Date date1, Date date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        System.out.println("data = "+sdf.format(date1));
        System.out.println("data1 = "+sdf.format(date2));
        c1.setTime(sdf.parse(sdf.format(date1)));
        c2.setTime(sdf.parse(sdf.format(date2)));

        int result = c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH);
        System.out.println("data = "+c1.get(Calendar.DAY_OF_MONTH));
        System.out.println("data1 = "+c2.get(Calendar.DAY_OF_MONTH));
        return result == 0 ? 1 : result+1;

    }
}
