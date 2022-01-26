# author-style-transform
## Table of Contents

- [Introduce](#introduce)
- [Function](#function)
- [Operation-Steps](#operation-steps)
- [Operating-environment](#operating-environment)

## Introduce

Change the original author's code style, a total of 19 types of code conversion, respectively, directional and non directional conversion

## Function
1. **Directional conversion**. All programs of the original author are transformed into the style of all target authors
2. **Non directional conversion**. All programs of the original author are transformed into the style of the target author.(Target Author: the target author with the greatest difference from the original program style)  

## Operation Steps
1. **Data processing**.
>	*If it is a new dataset,then Execute the `find . -name '*.c' ! -type d -exec bash -c 'expand -t 4 "$0" > /tmp/e && mv /tmp/e "$0"' {} \;` Process the C / C + + data set or Java first. After processing, it's better to save it and replace it with the original data. After that, you don't need to execute the second command (modify '*. c' according to your own data set)
2. **Enter transform directory**.
  >	* Put the test set in "./program_file/test" directory
  >	* Place the target author style data set in "./program_file/target_author_file” directory
  >	* Execute the `python get_style.py` command to generate the XML file of author style and program
  >		* output：  
  >		"./author_style" directory  
  >		* "./xml_file" directory
  >	* Directional conversion
  >		* run `python targeted_attack.py` command
  >		output: "./program_file/directional_file" directory
  >	* Non directional conversion
  >	  * run `python untargeted_attack.py --form=best`(The forms of transformation are best,random or all)command,output directory: "./program_file/nondirectional_file"

## Operating environment
> * Linux environment
> * Srcml (https://www.srcml.org/)
> * Python3 environment

