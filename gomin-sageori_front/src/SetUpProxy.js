const { createProxyMiddleware } = require("http-proxy-middleware");

const SetupProxy = (app)=>{
    app.use(
        createProxyMiddleware('/api', {
            target: 'http://localhost:8080',
            changeOrigin: true,
        })
    )
};

export default SetupProxy;