const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = app => {
  app.use(
    createProxyMiddleware('/api', {
      // domain
      target: process.env.REACT_APP_EC2_URL,
      changeOrigin: true,
    }),
  );
};
