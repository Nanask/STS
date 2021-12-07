# Spring TodoList ������Ʈ

##  MySQL, MyBatis�� ����� TodoList ������Ʈ
* mybatis 3.5 ���� ���
* MySQL 8.0.x ���� ���
* commons-dbcp2 ���

## �̹���(����) ÷�α�� �����ϱ�
* commons-io, commons-fileupload ����ϱ�

## fileUpload�� �����ϱ� ���Ͽ� context �����ϱ�
* multipartResolver�� �����Ͽ� fileUpload ��� ����
* context.xml���� multipartResolver�� �����Ͽ� �θ� form���� file tag�� ����Ͽ� file�� ���ε��ϸ� background���� HTTP �������ݿ� ������ ���ε��ϴ� ����� ÷���ǰ� Ư���� ����� �ڵ���� ���� ���ε� ����

## �Է� form ������ ��
* enctype="mulipart/form-data"�� �����صξ�� �Ѵ�.

## input file tag�� ���� ���� �����ϱ�
* accept�� �����Ͽ� ���� ������ �����ϱ�
* accept="image/*": (���) �̹��� ���ϵ鸸 ���ε��ϱ�
* accept="video/*": ������ ���ϸ� ���ε��ϱ�
* accept="text/plan": ���� text file�� �ø���(*.txt�� ����)
* accept="text/html": HTML �ڵ�� �� text ���ϸ� �ø���(*.htmnl)  
   ���� �ø��� �ʴ� ���� ����..
* accept="text/*": (���) text file �ø���
* accept=".jpg|.gif|.png": jpg, gif, png ���ϸ� �ø���
* accept=".xls|.xlsx": excel file�� �ø���
* accept="application/vnd.ms-excel": (���) excel file �ø���

## �������� ������ ����, ���ε��ϱ�
* input tag�� multiple="multiple" �Ӽ��� �߰��ϸ� ���� ���� ������ ������ �� �ִ�.

## bean ��� ������ ���Ͽ� �����ϱ� ���� ���
* http://localhost:8080
* https://www.naver.com
* file:///c:/users/my.txt
file:// ������ ���Ͽ� �����ϱ� ���� ��������
���Ͽ��� ���������� ����Ͽ� ���õ�ũ C ����̹�