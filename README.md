# Navyබඩී App <img src="https://user-images.githubusercontent.com/33552426/132079084-17fb09f4-fd62-4f88-a72b-7f204cb9ac7b.gif" alt="flag" width="50"/><img src="https://user-images.githubusercontent.com/33552426/132079084-17fb09f4-fd62-4f88-a72b-7f204cb9ac7b.gif" alt="flag" width="70"/><img src="https://user-images.githubusercontent.com/33552426/132079084-17fb09f4-fd62-4f88-a72b-7f204cb9ac7b.gif" alt="flag" width="50"/>

<img src="./images/NavyBuddy-app-screenshot.png" alt="NavyBuddy-app-screenshot" width="50%"/>


මෙම Android App එක ශ්‍රී ලංකා නාවික හමුදාවේ සේවය කරන ඔබ වෙනුවෙන් නිර්මාණය කර ඇති අතර, මෙම app එක මගින් <br/>
+ Short notes සටහන් කල හැකි අතර ඔබට අමතක වෙන, වැදගත් දේ එහි ඉක්මනින් සටහන් කරගත හැක.
+ Monthly Budget Calculator එක මගින් ඔබගේ මාසික ආදායම් හා වියදම් වල වෙනස ගණනය කල හැක.
+ Check Lists Manager එක මගින් ඔබ එදිනෙදා කරන දේවල් වල පියවරයන් වෙන් වෙන්ව List වල Save කර ඒම පියවර එකින් එක අවසන් කරගත හැක.
+ Epay View මගින් ඔබගේ Epay ගිණුමට පහසුවෙන් ගොස් ඔබගේ වැටුප් වාර්ථාව පහසුවෙන් බැලිය හැකි අතර ඔබගේ Username හා Password නිතර නිතර යෙදීමට අවශ්‍ය නැත.
+ Days Count Calculator එක මගින් ඔබ අවසන් වරට නිවාඩුගොස් පැමිණ අදට දින ගණන හා කදවුරේ රැදී සිටි දින ගණන පහසුවෙන් සෙවිය හැක.
+ Sealink View මගින් ඔබට sealink ගිණුමක් නැත්නම් පහසුවෙන් සාදාගත හැකි අතර sealink ගිණුමක් තිබේනම් sealink වෙත නිතර නිතර පහසුවෙන් යාමට හැක.
+ ඔබගේ මානසික සුවයට ඔබට කලහැකි ක්‍රියාකාරකම් සමූහයක්ද මෙහි අඩංගුය.
 

## NavyBuddy App එක ඔබගේ Android දුරකතනයට Install කරන ආකාරය:

1. පලමුව මෙම link එක මගින් මෙම මෘදුකාංගය download කරගන්න: <br />
1.0V - [NavyBuddy.apk](https://github.com/hankyoTutorials/navyBuddyApp/releases/download/V1.0-Beta/NavyBuddy.apk)

1. ඉන්පසු එම zip එක extract කරගන්න: 

    ![Extract-linux-sinhala-font-changer-zip](./res/instruction-1.jpg)

1. මීලගට එම "sinhala-font-changer" folder එක cut කර ඔබගේ home folder එකට paste කරන්න: 
    ![move-extracted-folder-to-home](./res/instruction-2.jpg) 
    ![move-extracted-folder-to-home-2](./res/instruction-3.jpg)

## භාවිත කරන ආකාරය:

1. linux terminal එකෙහි මෙම command 2ක type කිරීමෙන් මෙම මෘදුකාංගය open කරගතහැකිය:
    
    ```
    cd ~/sinhala-font-changer
    bash font-changer.sh
    ```

    ![run-commands-to-open-font-changer](./res/usage-instruction-1.jpg)

1. මෙහිදී "Enter" key එක press කරන්න:
    ![press-enter-to-continue](./res/usage-instruction-2.jpg)

1. මෙහි ඇති සිංහල font අතරින් ඔබට කැමති සිංහල font එකට අදාල අකුර type කර enter කරන්න:
    ![choose-a-font-to-install](./res/usage-instruction-3.jpg)

1. Complete!, ඉන්පසු සිංහල font එක වෙනස් වී ඇත්දැයි පරික්ෂා කර බලන්න
    ![installation-is-now-complete](./res/usage-instruction-4.jpg)

1. නැවතත් වෙනත් සිංහල font එකකට මාරුවීමට කැමතිනම් මෙම command 2ක type කර යලි මෙම මෘදුකාංගය 
open කරගතහැකිය:

    ```
    cd ~/sinhala-font-changer
    bash font-changer.sh
    ```

    ![change-to-another-font](./res/usage-instruction-5.jpg)

### මෙම මෘදුකාංගය නිර්මාණය කරඇති ආකාරය:

මෙහිදී `fontconfig` library එක හරහා සිංහල Unicode අකුරු සඳහා සිංහල font එකක් ආදේශනය කළ යුතු බව 
විශේෂයෙන් මෙහෙයුම් පද්ධතියට අවධාරණය කරයි, මේ සඳහා සැකසිය යුතු `fontconfig` configuration file 
සම්බන්ධයෙන් Arch Wiki හි පහත ලිපි වල විස්තරාත්මකව තතු ඉදිරිපත් කර ඇත.

+ [Font_configuration](https://wiki.archlinux.org/title/Font_configuration)
+ [Font_configuration/Examples](https://wiki.archlinux.org/title/Font_configuration/Examples)

## උපකාර

* [Github Issue](https://github.com/hankyoTutorials/linux-system-sinhala-font-changer/issues) එකක් 
open කිරීමෙන් උපකාර ලබා ගත හැකිය. 

Contact Adeepa: slrootkit@gmail.com (https://slrootkit.blogspot.com)<br/>
Contact Hankyo: hankyomail@gmail.com
