tiny-pmx-java
=============

Too simple PMX/PMD loader with Java
手軽にMMDモデルを表示したい人へ

PMX/PMDの超簡易ローダ（未完成）です。

- 最小限未満の機能
- 効率は無視
- 設計は適当
- No JavaDoc

＊＊現在頂点・麺・材質まで読めます。＊＊

PMDも一応読めますが内部でPMXに変換して格納してます。

Files
-----

+ src/
	+ Main.java - サンプルコード（processingのjarが必要）
+ src/com/na128/mmd/
      + Reader.java - ユーティリティ兼インターフェース
      + Pmx.java - 読み込んだデータの格納
      + PmxReader.java - PMXパーサ
      + PmdReader.java - PMDパーサ<br />
      + ChunkReader.java - 内部用<br />
+ src/com/na128/mmd/data/ - 各データ構造体

Example
-------
  
  
  Pmx pmx = Reader.read(new File("path/to/pmx_or_pmd.pmx")); // 読み込み
  
  
  pmx.getHeader().getVersion(); // ファイルのバージョン取得

License
-------

ソースコードそのものはほぼPDSですが、ファイルフォーマットはPmxEditorのPMX仕様に基づいているので商用利用ができないなどの制約があります。注意してください。
  
