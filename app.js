const http = require('http');
const os = require('os');

port = getEnv("PORT", "8080");

console.log("AppX server starting...");

var handler = function(request, response) {
  console.log("Received request from " + request.connection.remoteAddress);
  response.writeHead(200);
  response.end("Hello from " + os.hostname() + "\n");
};
var www = http.createServer(handler);
www.listen(port, function (err) {
  console.log('listening http://localhost:'+port+"/");
  console.log('pid is ' + process.pid);
});

process.on('SIGTERM', function () {
  server.close(function () {
    process.exit(0);
  });
});

function getEnv(key, defaultValue) {
  value = process.env[key]
  if (value == null ) {
    console.log("Setting variable " + key + " to default value:", defaultValue)
    return defaultValue
  }
  return value
}