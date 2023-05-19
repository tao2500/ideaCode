const path = require('path');
let CopyPlugin = require("copy-webpack-plugin");

module.exports = {
    entry: './a.js',
    output: {
        // 输出到当前目录下的’‘文件夹
        path: path.resolve(__dirname, ''),
        filename: 'bundle1.js'
    },
    // 配置loader 同插件、使用前应先用cli下载
    module: {
        rules: [{
            test: /\.css$/,
            // css-loader是必需的，它的作用是解析CSS文件，包括解析@import等CSS自身的语法。它的作用也仅仅是解析CSS文件，它会把CSS文件解析后，以字符串的形式打包到JS文件中
            // style-loader就来发挥作用了，它可以把JS里的样式代码插入到html文件里。它的原理很简单，就是通过JS动态生成style标签插入到html文件的head标签里。
            use: ['style-loader', 'css-loader']
        }]
    },
    // 配置插件
    // 常用：clean-webpack-plugin是一个清除文件的插件。在每次打包后，磁盘空间会存有打包后的资源，在再次打包的时候，我们需要先把本地已有的打包后的资源清空，来减少它们对磁盘空间的占用。
    // 即clean-webpack-plugin会先清空path: path.resolve(__dirname, '')指定的文件夹，然后再把结果写入该文件夹
    // 插件copy-webpack-plugin是用来复制文件的。在我们使用Webpack的时候，有一些本地资源，例如图片和音视频，在打包过程中没有任何模块使用到它们，但我们却想要把它们放在打包后的资源输出目录。
    plugins: [
        new CopyPlugin({
            patterns: [
                { from: path.resolve(__dirname, '../css/b.css'), to: path.resolve(__dirname, '') },
            ],
        }),
    ],
    mode: 'production'
};
