const { createProxyMiddleware } = require("http-proxy-middleware");

const SetUpProxy = (app) => {
    app.use(
        createProxyMiddleware("/api", {
            //domain
            target: "http://localhost:8080",
            changeOrigin: true,
        })
    );
}

export default SetUpProxy;
