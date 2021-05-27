# Steganography for copyrighting

This proyect has the purpose of helping copyrighting laws by applying techniques to hide information at plain sight.

## How to run

This repo also includes the .jar file, the one needed to run the proyect without compiling the repo in java. The jar file needs some information:

- A **jpg image**. The name of the image + jpg extension file. ex: "image.jpg"
- The **route** where the image is located. ex: "C:\Users\user\Documents\proyect"
- a **message** to encrypt

The steganography program lets you encrypt and decrypt an image, with both funcionalities.

## Steps

**To Encrypt**
1. Open the file "stegjava.jar (or download it if it's not).
2. On te left side is the encryption menu, enter the message to hide, the image route and the image name, finally press "hide".
3. The result should be an image named "steg1.jpg".

**To Decrypt**
1. Open the file "stegjava.jar (or download it if it's not).
2. On the right side is the decryption menu, insert the encrypted image name, the route and click "recover".
3. The result should be the recovered message.

On both cases the parallel and sequential execution times will be on the bottom.

