# author-style-transform
## Table of Contents

- [Introduce](#introduce)
- [Function](#function)
- [Operation-Steps](#operation-steps)
- [Operating-environment](#operating-environment)

## Introduce

Change the original author's code style, a total of 19 types of code conversion, respectively, directional and non directional conversion

## Function
### Directional conversion**. 
All programs of the original author are transformed into the style of all target authors
### Non directional conversion. 
All programs of the original author are transformed into the style of the target author.(There are any target authors, so that the transformed program can attack successfully)  
  i. First, all the programs are transferred to the optimal target author, and the original program with successful attack is selected through model test.
  ii. Turn the original program that failed to attack to all target authors, and then select the original program that succeeded in attack through model test, and add it to the original program that succeeded in the first step, that is the original test set of all successful attacks.

## Operation Steps
1. **Data processing**. `find . -name '*.c' ! -type d -exec bash -c 'expand -t 4 "$0" > /tmp/e && mv /tmp/e "$0"' {} \;` Process the C / C + + data set or Java first. After processing, it's better to save it and replace it with the original data. After that, you don't need to execute the second command (modify *. C according to your own data set)
2. **Enter test_transform directory**.
1) Put the test set in "./program_file/test" directory
2) Place the target author style data set in "./program_file/target_author_file” directory
3) Execute the `python scan.py` command to generate the XML file of author style and program
   output："./author_style" directory
		     "./xml_file" directory
4) Directional conversion
   a)	run `python directional_transform.py` command
   b)	output: "./program_file/directional_file" directory
5) Non directional conversion
   a)	run `python non_directional.py 1` command ,find the appropriate target author,leave the non directional success program after model testing, output directory: "./program_file/nondirectional_file"
   b)	put the non directed attack success in the './tool/data_precessing'
   c)	get into tool directory, run `python deal_non_dir.py 1`
   d)	get into test_transform directory, run `python non_directional.py all` transform to all target authors,leave the non directional success program after model testing
   e)	put the non directed attack success in the './tool/data_precessing'
   f)	get into tool directory, run `python deal_non_dir.py 2`, each program has more than one target author. If the attack is
successful,only one needs to be left, and all others need to be deleted

## Operating environment
1.	Linux environment
2.	Srcml(https://www.srcml.org/)
3.	Python3 environment

