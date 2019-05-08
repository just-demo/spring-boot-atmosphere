var ws = new WebSocket('ws://localhost:8080/test');
ws.onopen = () => {
  console.log('Connection opened!');
  ws.close(3005, 'Test reason');
};

ws.onmessage = event => {
  console.log(`Received: ${event.data}`);
};

ws.onclose = () => {
   console.log('Connection closed!');
};
