# MaxEnt-ChineseDependencyParse
GIS算法，基于openNLP最大熵接口的依存句法分析训练器，支持多核并行，大幅提高训练效率
训练文件<feature, lable>
适配不同内存与处理器时，修改cutoff和threads参数，另外注意调大堆内存
Usage: java CreateModel [-real] [-perceptron] dataFile iteration cutoff threads
