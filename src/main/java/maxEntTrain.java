
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import com.sun.javaws.IconUtil;
import opennlp.maxent.GISModel;
import opennlp.maxent.io.SuffixSensitiveGISModelWriter;
import opennlp.model.*;
import opennlp.tools.util.ObjectStream;
import opennlp.maxent.GIS;

import java.io.IOException;


public class maxEntTrain {
    public static void main(String[] args) throws IOException {
        //传入参数至少两个[训练文件路径与模型位置],可以有五个,[iterations][cutoff][threads]
        int iterations = 100;
        int cutoff = 2;
        int threads = 1;
        if (args.length < 2){
            System.out.println("At least two paraments: trainfilepath and modelfilepath.");
            return;
        }
        else if (args.length == 2){
            System.out.println("cutoff:0, iterations:100.");
        }
        else if (args.length == 3){
            iterations = Integer.parseInt(args[2]);
            System.out.println("Three paraments.");
        }
        else if (args.length == 4){
            iterations = Integer.parseInt(args[2]);
            cutoff = Integer.parseInt(args[3]);
            System.out.println("Four paraments.");
        }
        else if (args.length == 5){
            iterations = Integer.parseInt(args[2]);
            cutoff = Integer.parseInt(args[3]);
            threads = Integer.parseInt(args[4]);
            System.out.println("Five paraments.");
        }
        //训练maxEnt模型
        String trainingFileName = args[0];
        String modelFileName = args[1];

        long start = System.currentTimeMillis();
//        DataIndexer indexer = new OnePassDataIndexer();
//        ObjectStream fromFile = new FileEventStream(trainingFileName);
//        indexer.index(fromFile);
//        GISTrainer maxEntTrainer = new GISTrainer();
//        GISModel maxEntModel = maxEntTrainer.trainModel(iterations, indexer, threads);
        EventStream eventStream = new FileEventStream(trainingFileName);
        DataIndexer indexer = new OnePassDataIndexer(eventStream, cutoff);
        Prior prior = new UniformPrior();
        GISModel maxEntModel = GIS.trainModel(iterations, indexer,true, false, prior, cutoff, threads);
        long end = System.currentTimeMillis();
        float timeCost = (float)(System.currentTimeMillis() - start) / 1000;
        System.out.print("Time cost: ");
        System.out.println(timeCost);

        //将训练好的模型存储到文件中供以后使用
        File outFile = new File(modelFileName);
        AbstractModelWriter writer = new SuffixSensitiveGISModelWriter((AbstractModel)maxEntModel, outFile);
        writer.persist();

        //从文件加载训练好的模型
        //FileInputStream inputStream = new FileInputStream(modelFileName);
//        InputStream decodedInputStream = new GZIPInputStream(inputStream);

//      BinaryFileDataReader modelReader = new BinaryFileDataReader(inputStream);//读取二进制
////        DataReader modelReader = new PlainTextFileDataReader(inputStream);
//        MaxentModel loadedMaxentModel = new GISModelReader(modelReader).getModel();
//
//        //现在使用加载的模型预测结果
//        String[] context = {"等i-2", "udengi-2", "经济i-1", "ni-1", "犯罪i0", "vni0", "##空白##i1" ,"nulli1" ,"##空白##i2", "nulli2" ,"##核心##j-2", "rootj-2" ,"坚决j-1" ,"adj-1" ,"惩治j0", "vj0", "贪污j1" ,"vj1", "贿赂j2" ,"nj2", "犯罪→惩治" ,"vn→v" ,"犯罪→惩治5" ,"vn→v5", "经济@犯罪→惩治" ,"犯罪→坚决@惩治", "n@vn→v", "vn→ad@v"};
//        double[] outcomeProbs = loadedMaxentModel.eval(context);
//        String outcome = loadedMaxentModel.getBestOutcome(outcomeProbs);
//        System.out.println(outcome);
    }
}