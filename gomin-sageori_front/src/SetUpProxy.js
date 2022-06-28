const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = (app) => {
    app.use(
        createProxyMiddleware('/api', { //domain
            target: 'http://localhost:8080', //express server add
            changeOrigin: true,
        })
    )
};