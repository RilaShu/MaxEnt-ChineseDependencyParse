# MaxEnt-ChineseDependencyParse
基于openNLP最大熵接口的依存句法分析训练器  
THU数据集  
使用Hanlp进行数据转化  
适配不同内存与处理器时，修改cutoff和threads参数，另外注意调大堆内存
Usage: java CreateModel [-real] [-perceptron] dataFile iteration cutoff threads
