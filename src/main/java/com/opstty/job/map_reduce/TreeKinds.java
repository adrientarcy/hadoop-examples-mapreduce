package com.opstty.job.map_reduce;

import com.opstty.mapper.TokenizerMapper;
import com.opstty.mapper.TreeKindsMapper;
import com.opstty.reducer.TreeKindsReducer;
import com.opstty.reducer.IntSumReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class TreeKinds {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: district_trees <in> [<in>...] <out>");
            System.exit(2);
        }
        Job job = Job.getInstance(conf, "tree_kinds");
        job.setJarByClass(DistrictTrees.class);
        job.setMapperClass(TreeKindsMapper.class);
        job.setCombinerClass(TreeKindsReducer.class);
        job.setReducerClass(TreeKindsReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        for (int i = 0; i < otherArgs.length - 1; ++i) {
            FileInputFormat.addInputPath(job, new Path(otherArgs[i]));
        }
        FileOutputFormat.setOutputPath(job,
                new Path(otherArgs[otherArgs.length - 1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
