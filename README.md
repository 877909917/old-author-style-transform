# author-style-transform
## Table of Contents

- [Introduce](#introduce)
- [Function](#function)
- [Operation-Steps](#operation-steps)
- [Operating-environment](#operating-environment)

## Introduce

Change the original author's code style, a total of 19 types of code conversion, respectively, directional and non directional conversion

1. 
2. **An example README**. This Readme is fully standard-readme compliant, and there are more examples in the `example-readmes` folder.
3. A **linter** that can be used to look at errors in a given Readme. Please refer to the [tracking issue](https://github.com/RichardLitt/standard-readme/issues/5).
4. A **generator** that can be used to quickly scaffold out new READMEs. See [generator-standard-readme](https://github.com/RichardLitt/generator-standard-readme).
5. A **compliant badge** for users. See [the badge](#badge).

## Function
1. **Directional conversion**. All programs of the original author are transformed into the style of all target authors
2. **Non directional conversion**. All programs of the original author are transformed into the style of the target author.(There are any target authors, so that the transformed program can attack successfully)  
   1）First, all the programs are transferred to the optimal target author, and the original program with successful attack is selected through model test.
   2）Turn the original program that failed to attack to all target authors, and then select the original program that succeeded in attack through model test, and add it to the original program that succeeded in the first step, that is the original test set of all successful attacks.

## Operation Steps
1. **Data processing**. `find . -name '*.c' ! -type d -exec bash -c 'expand -t 4 "$0" > /tmp/e && mv /tmp/e "$0"' {} \;` Process the C / C + + data set or Java first. After processing, it's better to save it and replace it with the original data. After that, you don't need to execute the second command (the black bold part should be modified according to your own data set)

## Operating environment

To use the generator, look at [generator-standard-readme](https://github.com/RichardLitt/generator-standard-readme). There is a global executable to run the generator in that package, aliased as `standard-readme`.
