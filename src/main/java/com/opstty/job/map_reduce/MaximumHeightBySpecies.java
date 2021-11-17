package com.opstty.job.map_reduce;

import com.opstty.mapper.MaximumHeightBySpeciesMapper;
import com.opstty.reducer.MaximumHeightBySpeciesReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class MaximumHeightBySpecies {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: existing_species <in> [<in>...] <out>");
            System.exit(2);
        }
        Job job = Job.getInstance(conf, "height_species_max");
        job.setJarByClass(MaximumHeightBySpecies.class);
        job.setMapperClass(MaximumHeightBySpeciesMapper.class);
        job.setCombinerClass(MaximumHeightBySpeciesReducer.class);
        job.setReducerClass(MaximumHeightBySpeciesReducer.class);
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
