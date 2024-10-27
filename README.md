# Android virtual camera

- Virtual camera based on Xposed

# Please do not use it for any illegal purposes, and you will bear all the consequences!!

## Use the demonstration

- https://fastly.jsdelivr.net/gh/iiheng/TuChuang@main/1700961311425EasyGIF-1700961287297.gif

## Development plan

- [x] Support rtmp transmission live broadcast to improve stability

- [ ] Support video selection in advance and customise the playback order

## Development environment

- Android SDK 34.

- Xposed 82

- Xiaomi 9 MIUI 11.0.3

- Xiaomi 8 MIUI 11.0.3

- Redim K40 MUI 14.0.7

- Kubi Rubik's Cube 50pro MIUI 14.0.5

- Last Lsposed

## How to use

1. Check the playback platform you want in Lsposed

2. Select the video you want to play in the software.

3. Turn on the video switch

4. Then select the platform to play

## Support replacement

1. Support video replacement

2. Support RTMP live replacement, unstable

## Precautions

1. Video playback needs to be the same format as the platform playback, and basically supports 9:16 video, such as: 3840x2160, 1920x1080,1280x720,854x480,640x360,426x240,256x144

2. The screen is black, and the camera failed to start, because there is a problem with video decoding. Please click multiple times to flip the camera.

3. The screen is flipped and does not match the original video. The current video playback has not been adjusted. Please adjust the video manually.

4. Different software has different requirements for hard decoding and soft decoding. If there is only sound but no picture many times, please switch the video decoding method.

5. Hard decoding is smoother than soft decoding. Please judge whether hard decoding is supported according to your mobile phone model. Soft decoding is more adaptable, and videos basically support playback.

## Feedback questions

- Telegram: [Click to join] (https://t.me/+WbEK_suGxG9mZGM1)

- Feedback in issues. If it is feedback for BUG, please attach the Xposed module log information.

## Thank you

- Provide hook code: https://github.com/Xposed-Modules-Repo/com.example.vcam
