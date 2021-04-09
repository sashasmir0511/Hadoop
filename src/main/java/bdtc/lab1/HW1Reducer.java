package bdtc.lab1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Редьюсер: суммирует все единицы полученные от {@link HW1Mapper}, выдаёт суммарное количество пользователей по браузерам
 */
public class HW1Reducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    /*private static Text keyWritable = new Text();
    private static MapWritable dictWritable = new MapWritable();
    private static String[] values_key =
            {"bot", "body", "top"};

     */

    /*@Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        keyWritable = new Text();
        dictWritable = new MapWritable();

        while (values.iterator().hasNext()){
            int element = values.iterator().next().get();
            IntWritable valueWritable = new IntWritable();
            keyWritable.set(new Text(values_key[element]));
            if (dictWritable.get(keyWritable) != null) {
                valueWritable = (IntWritable) dictWritable.get(keyWritable);
                valueWritable.set(valueWritable.get() + 1);

                dictWritable.put(new Text(keyWritable), valueWritable);
            }
            else {
                valueWritable.set(1);
                dictWritable.put(new Text(keyWritable), valueWritable);
            }
        }
        context.write(key,  dictWritable);
    }*/
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;

        while (values.iterator().hasNext()) {
            sum += values.iterator().next().get();
        }
        context.write(key, new IntWritable(sum));
    }
}
