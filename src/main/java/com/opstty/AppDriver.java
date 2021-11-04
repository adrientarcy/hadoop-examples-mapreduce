package com.opstty;

import com.opstty.job.WordCount;
import com.opstty.job.map_reduce.*;
import org.apache.hadoop.util.ProgramDriver;

public class AppDriver {
    public static void main(String argv[]) {
        int exitCode = -1;
        ProgramDriver programDriver = new ProgramDriver();

        try {
            programDriver.addClass("wordcount", WordCount.class,
                    "A map/reduce program that counts the words in the input files.");

            programDriver.addClass("district_trees", DistrictTrees.class,
                    "A map/reduce program that counts the trees in the districts.");

            programDriver.addClass("existing_species", ExistingSpecies.class,
                    "A map/reduce program that lists all the existing tree species");

            programDriver.addClass("tree_kinds", TreeKinds.class,
                    "A map/reduce program that lists the number of trees by kind");

            exitCode = programDriver.run(argv);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.exit(exitCode);
    }
}
