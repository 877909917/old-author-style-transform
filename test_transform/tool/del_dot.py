"""
Process the test set, delete the program file and the author directory that are empty
"""
import os
import subprocess
test_path = './test'


def cmd(command):
    print(command)
    global flag
    flag = True
    subp = subprocess.Popen(command,shell=True,stdout=subprocess.PIPE,stderr=subprocess.PIPE,encoding="utf-8")
    subp.wait(10)
    if subp.poll() == 0:
        flag = True
    else:
        print("False!")
        flag = False


if __name__=="__main__":
    for dir in os.listdir(test_path):
        files_path = os.path.join(test_path, dir)
        # program file is empty,then delete
        for filename in os.listdir(files_path):
            filename_path = os.path.join(files_path, filename)
            print(filename_path)
            size = os.path.getsize(filename_path)
            if size == 0:
                print(filename)
                command = 'rm -rf \"' + filename_path + '\"'
                cmd(command)
        # author directory is empty,then delete
        if len(os.listdir(files_path)) == 0:
            print(files_path)
            command = 'rm -rf \"' + files_path + '\"'
            cmd(command)
