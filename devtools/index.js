const chokidar = require('chokidar');
const { exec } = require('child_process');
const path = require('path');
require('dotenv').config({ path: path.resolve(__dirname, '../.env.commands') });

const command = process.env.BUILD_COMMAND;
let javaPid;

const watcher = chokidar.watch('src', {
  ignored: /(^|[\/\\])\../, // ignore dotfiles
  persistent: true,
});

watcher.on('change', (path) => {
  console.log(`File changed: ${path}`);
  if (javaPid) {
    killProcess(javaPid);
  } else {
    startProcess();
  }
});

function startProcess() {
  console.log('Starting process...');
  const javaProcess = exec(command);
  javaProcess.stdout.on('data', (data) => {
    console.log(data);
    const match = data.toString().match(/PID: (\d+)/);
    if (match) {
      javaPid = parseInt(match[1]);
      console.log(`Java PID: ${javaPid}`);
    }
  });
}

function killProcess(pid) {
  console.log('Killing previous process...');
  exec(`taskkill /PID ${pid}`, (error) => {
    if (error) {
      console.log(`Error killing process: ${error}`);
      if (error.message.includes('not found')) {
        console.log('Process not found, starting a new one...');
        startProcess();
      }
    } else {
      console.log('Previous process killed.');
      startProcess();
    }
  });
}

startProcess();
