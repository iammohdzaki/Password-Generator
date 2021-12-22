
[![](https://jitpack.io/v/iammohdzaki/Password-Generator.svg)](https://jitpack.io/#iammohdzaki/Password-Generator)
# Password-Generator & CLI Application

This is Android/Kotlin/Java Library which generates passwords for you and also provide the strength rating of your password using zxcvbn4j library.
- It also has a cli application.

For Gradle : 
1. Add the JitPack repository to your build file
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2. Add the dependency
```
dependencies {
	        implementation 'com.github.iammohdzaki:Password-Generator:latest_version'
	}
```

For Maven : 
1. Add the JitPack repository to your build file
```
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
2. Add the dependency
```
	<dependency>
	    <groupId>com.github.iammohdzaki</groupId>
	    <artifactId>Password-Generator</artifactId>
	    <version>Latest Version</version>
	</dependency>
```

How To Use

Create a Builder :
```
 //All Filters are optional
 PasswordGenerator.Builder(PasswordType.RANDOM) // Password Generation Type
            .showLogs(true) // Shows Logs Inside the Library (FOR TEST CASES)
            .includeUpperCaseChars(true) // Include Upper Case in password
            .includeNumbers(true) // Include Numbers in password
            .includeLowerCaseChars(true) // Include Lower Case in password
            .includeSpecialSymbols(true) // Include Special Symbols in password
            .passwordLength(12) // Length of the password (ONLY WORKS IN RANDOM TYPE)
	    .customPassword("")//If you want to create custom password from your string (ONLY WORKS WITH PASSWORD TYPE :CUSTOM)
            .callback(object : PasswordGenerator.Callback {
                override fun onPasswordGenerated(response: Response) {
          
                   //Response provides two object 
                   // password : String = "",
                   // strengthResult: StrengthResult
                 
                }
            })
            .build()
            .generate()
```

Strength Result :
```
crackTimeDisplay : 9 years
passwordRating : (0 Weak,1 Fair,2 Good,3 Strong ,4 Very strong) : 4/4
feedback : [Add another word or two. Uncommon words are better.]
crackTimeInSecS :  3.0001E8

```

Types of Password Generation : 
```
(THESE EXAMPLES ARE GENERATED WITH ALL FILTERS ENABLED)
PasswordType.RANDOM  // Eg : 1D8dm7j%1hN*&NL@eU/14!XeX
PasswordType.MEMORABLE // Eg : s#0rtW@v369&=  (shortwave)
PasswordType.DASHED / /Eg : ?0Ad-J*!U
PasswordType.CUSTOM //Eg: Provided String = "MohammadZaki" , Generated Password : " m*#@mm@dz@k!%76"
```

Additional Feature :
If you only want to check strength of your password, then use :
```
GeneratorHelper.checkPasswordStrength(password, showLogs) // For Test Case
GeneratorHelper.checkPasswordStrength(password) // For Normal Usel̥
```

How To Use CLI Application (Check Branch -> master-cli):
```
Usage: Password Generator CLI :: 0.1
Subcommands:
    include - Include Filters( upper lower digits special )

Options:
    --version, -version [false] -> Shows Version
    --passwordType, -type [RANDOM] -> Password Type { Value should be one of [random, memorable, dashed, custom] }
    --passwordLength, -length [8] -> Set Password Length { Int }
    --logs, -showLogs [false] -> Logs
    --custom, -custom [GENERATION] -> Custom Password { String }
    --help, -h -> Usage info
```

Example : 
```
//Inside bin folder open cmd to execute commands
> PasswordGenerator -type random -length 12 -showLogs include upper lower

Result :
PASSWORD TYPE -> RANDOM
GENERATING RANDOM PASSWORD
GENERATED PASSWORD -> YFfHhPMvSZTf
STRENGTH CHECK -> YFfHhPMvSZTf
TIME TO CRACK DISPLAY: 3 years
STRENGTH RATING (0 Weak,1 Fair,2 Good,3 Strong ,4 Very strong) : 4/4
VERBAL_FEEDBACK : []
GUESSED NEEDED : 12.000000000000433
CRACK IN SECONDS : 1.000000000001E8

Password Generator CLI (0.1)
YFfHhPMvSZTf
Password Generated Successfully!
```


License
```
The MIT License (MIT)

Copyright (c) 2021 Mohammad Zaki

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
