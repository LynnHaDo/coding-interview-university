import os;
import subprocess;
import sys;
import argparse;

parser = argparse.ArgumentParser(prog="project_setup", description="Project folder setup program for practice project.")
parser.add_argument('topic', 
                    metavar='FOLDER_NAME', 
                    type=str, 
                    help='Name of topic to set up a new project')
parser.add_argument('implementation_setup',
                    metavar="IMPLEMENTATION_REQUIRED",
                    type=bool,
                    help="Whether or not c implementation files should be generated")

args = parser.parse_args() 

def get_maven_task_command(parentDir):
    """
    Generate command to create a maven project

    :param parentDir: path to parent directory to generate the maven project into
    :return: the command as a string
    """

    return f"mvn archetype:generate -DgroupId=leetcode -DartifactId={parentDir} -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false"


def java_project_setup(parentDir):
    """
    Set up java folder (maven)

    :param parentDir: path to generate the project in
    """
    try: 
        print(f"Generating java project within {args.topic}...\n")
        subprocess.call(get_maven_task_command(parentDir), shell=True)
    except OSError as e:
        print(f"An error occured: {e}\n")
        sys.exit(1)

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
        print("Folder name already exists within this directory. The setup will generate files in this existing folder...")

    os.makedirs(args.topic)

    if (args.implementation_setup):
        srcPath = os.path.join(args.topic, 'impl')
        # Set up c files
        c_implementation_setup(srcPath)
    
    # Set up java files
    java_project_setup(args.topic)











