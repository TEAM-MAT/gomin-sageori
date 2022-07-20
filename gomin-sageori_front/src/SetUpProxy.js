const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = app => {
  app.use(
    createProxyMiddleware('/api', {
      // domain
      target: 'http://15.164.111.113:8080',
      changeOrigin: true,
    }),
  );
};
