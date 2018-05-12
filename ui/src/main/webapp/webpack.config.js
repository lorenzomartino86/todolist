const webpack = require('webpack');

var path = require('path');

const SRC_DIR = path.resolve(__dirname, '');
const BUILD_DIR = path.resolve(__dirname, '../../../target/classes/static');

console.log('##### BUILD_DIR', BUILD_DIR);
console.log('##### SRC_DIR', SRC_DIR);

module.exports = {
    entry: SRC_DIR + '/index.js',
    devtool: 'source-map',
    cache: true,
    output: {
        path: BUILD_DIR,
        filename: 'app.bundle.js'
    },
    externals: {
        'react/addons': true,
        'react/lib/ExecutionEnvironment': true,
        'react/lib/ReactContext': true,
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        cacheDirectory: true,
                        presets: ['es2015', 'react']
                    }
                }


            }
        ]
    },
    resolve: {
        extensions: ['.js', '.jsx']
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new webpack.NamedModulesPlugin()
    ],
    devServer: {
        port: 9090,
        proxy: {
            '/': {
                target: 'http://localhost:8080/presentation',
                secure: false,
                prependPath: false
            }
        },
        headers: {
            'Access-Control-Allow-Origin': '*'
        },
        publicPath: 'http://localhost:9090/presentation',
        historyApiFallback: true
    },
};