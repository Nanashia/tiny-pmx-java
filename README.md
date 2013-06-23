tiny-pmx-java
=============

Too simple PMX/PMD loader with Java
手軽にMMDモデルを表示したい人へ

PMX/PMDの超簡易ローダ（未完成）です。

- 最小限未満の機能
- 効率は無視
- 設計は適当
- No JavaDoc


PMDも一応読めますが内部でPMXに変換して格納してます。


// 読み込み
Pmx pmx = Reader.read(new File("path/to/pmx_or_pmd.pmx"));

// ファイルのバージョン取得
pmx.getHeader().getVersion();

= License =
ソースコードそのものはほぼPDSですが、ファイルフォーマットはPmxEditorのPMX仕様に基づいているので商用利用ができないなどの制約があります。注意してください。
