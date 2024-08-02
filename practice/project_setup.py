import os;
import subprocess;
import sys;
import argparse;

# TODO: Implement function to update pom.xml version (Reference: https://gist.github.com/CrowdSalat/204a50cc0681ec3531d804e70e285aa3)

parser = argparse.ArgumentParser(prog="project_setup", description="Project folder setup program for practice project.")
parser.add_argument('topic',
                    metavar='FOLDER_NAME', 
                    type=str, 
                    help='Name of topic to set up a new project')
parser.add_argument('-impl',
                    '--implement',
                    type=int,
                    help="Whether or not c implementation files should be generated (1 for yes, 0 for no)")
parser.add_argument("-c",
                    "--compile",
                    type=int,
                    help="Whether or not to compile the maven directory (1 for yes, 0 for no)")

args = parser.parse_args() 

def get_maven_task_command(parentDir):
    """
    Generate command to create a maven project

    :param parentDir: path to parent directory to generate the maven project into
    :return: the command as a string
    """

    return f"mvn archetype:generate -DgroupId=leetcode -DartifactId=leetcode.{parentDir.lower().replace(' ', '')} -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false"


def java_project_setup(parentDir):
    """
    Set up Java folder (maven)

    :param parentDir: path to generate the project in
    """
    try: 
        print(f"Generating java project within {args.topic}...\n")
        # Run mvn command
        subprocess.call(get_maven_task_command(parentDir), shell=True, cwd=parentDir)
    except OSError as e:
        print(f"An error occured: {e}\n")
        sys.exit(1)

def java_project_compile(dir):
    """
    Compile maven project 

    :param dir: directory of the maven project
    """
    subprocess.call("mvn compile", shell=True, cwd=dir)

def c_implementation_setup(srcPath):
    """
    C implementation files setup

    :param srcPath: path to generate the project in

    :return OSError: if any folder, .c, .h, .py already exists
    """
    try: 
        print(f"Generating implementation files within {args.topic}...\n")
        os.makedirs(srcPath) # generate a folder for c implementation
        open(os.path.join(srcPath, f"{args.topic.lower()}.c"), 'a').close()
        open(os.path.join(srcPath, f"{args.topic.lower()}.h"), 'a').close()
        open(os.path.join(srcPath, f"{args.topic.lower()}_test.py"), 'a').close()
    except OSError as osError:
        print(f"File name already exists. Error: {osError}")

if __name__ == "__main__":
    if (os.path.exists(args.topic)):
        print("Folder name already exists within this directory.\nThe setup will generate files in this existing folder...")
    else:
        os.makedirs(args.topic)
    
    # Set up PATH environment variable
    os.environ['MAVEN'] = "/Users/linhdo/Documents/Coding/coding-interview-university/lib/apache-maven-3.9.8/bin"
    os.environ['PATH'] = f"{os.environ['MAVEN']}:{os.environ['PATH']}"

    if (args.implement == 1):
        srcPath = os.path.join(args.topic, 'impl')
        # Set up c files
        c_implementation_setup(srcPath)
    
    if (args.compile == 1):
        if (not os.path.exists(f"{args.topic}/leetcode.{args.topic.lower().replace(' ', '')}")):
            print("Leetcode Java project does not exist.")
            # Set up java files
            java_project_setup(args.topic)
        
        java_project_compile(f"{args.topic}/leetcode.{args.topic.lower().replace(' ', '')}")
        sys.exit(1)
    
    java_project_setup(args.topic)
    
    











