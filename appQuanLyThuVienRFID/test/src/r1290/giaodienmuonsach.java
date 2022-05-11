package r1290;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class giaodienmuonsach implements ActionListener {

	MyconnectUnit Connectunit;
    MySQLConnect connect;
	
    JFrame jframe = new JFrame("Mượn sách thư viện");
    
	JLabel tieude= new JLabel();
	JLabel nhapmahoadon = new JLabel();
	JLabel htketqua = new JLabel();
	
	JTextField nhaphd = new JTextField();
	JTextArea ketqua = new JTextArea();
	
	JButton thuchien= new JButton();
	
	Vector header;
	DefaultTableModel model;
	
	JLabel thanhcong;
	
	public giaodienmuonsach() {
		
		//nhập hóa đơn
		nhapmahoadon.setBounds(new Rectangle(90,450,140,30));
		nhapmahoadon.setText("nhập mã mượn: ");
		jframe.add(nhapmahoadon);
		
		nhaphd.setBounds(new Rectangle(180,455,80,20));
		jframe.add(nhaphd);
		
		//button
		thuchien.setBackground(Color.yellow);
		thuchien.setOpaque(true);
		thuchien.setBounds(new Rectangle(350,500,100,30));
		thuchien.setText("Duyệt");
		jframe.add(thuchien);
		thuchien.addActionListener(this);
		
		//thành công
		thanhcong = new JLabel();
		thanhcong.setBounds(new Rectangle(300,450,200,30));
		jframe.add(thanhcong);
		
		//bảng hiển thị
		header = new Vector();
        header.add("Mă sách");
        header.add("Tên sách");
        header.add("Trạng thái");
        
        model = new DefaultTableModel(header,0);
        
        
        JTable tbl = new JTable(model);
        
        JScrollPane sp = new JScrollPane(tbl);
        jframe.add(sp);
        
		jframe.setSize(800, 600);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == thuchien) {
			//nhập hóa đơn
			String a = nhaphd.getText();
			ketqua.setText(a);
			thanhcong.setText("");
			model.setNumRows(0);
			//--------------------------------------------------------
			//khai báo mảng và classDAO
			try {
				ArrayList ArrIDBook = new ArrayList<>();
				 BorrowDetailDAO dao = new BorrowDetailDAO();
				//khai báo mảng và class của book
				BookDAO book = new BookDAO();
				ArrayList<BookDTO> listBook = new ArrayList<BookDTO>();
				//khia báo mảng và class của borrow
				BorrowDAO borrow = new BorrowDAO();
				ArrayList<BorrowDTO> listBorrow = new ArrayList<BorrowDTO>();
				//gắn cờ
				int flag = 1;
				int kthd = 0;
				
				//lấy id book từ hóa đơn
		        ArrIDBook = dao.LayMangBookDangKi(a);
		        listBook = book.docDssp();
		        listBorrow = borrow.docDssp();
		        
		        // kiểm tra trạng thái mã mượn qua status
		        for(int i=0; i<listBorrow.size();i++) {
		        	if(listBorrow.get(i).borrow_id.equals(a)) {
			        	if(listBorrow.get(i).borrow_status == 1) {
			        		System.out.println("Mã mượn này đã được duyệt");
			        		thanhcong.setText("mã này đã được sử dụng rồi");
			        		//System.exit(0);
			        		kthd = 1;
			        	}
		        	}
		        }
		        
		        ArrayList<rfidDTO> MyTags = new ArrayList<rfidDTO>();
//				MyTags.add(new rfidDTO("5", "ant0", null, 0));
				MyTags.add(new rfidDTO("4", "ant0", null, 0));
//				MyTags.add(new rfidDTO("1", "ant0", null, 0));
				MyTags.add(new rfidDTO("2", "ant0", null, 0));
				MyTags.add(new rfidDTO("3", "ant0", null, 0));
				
				ArrayList ArrTagID = new ArrayList<>();
				
				for (int i = 0; i < MyTags.size(); i++)
		        {
//		            System.out.println("EPC: "+ MyTags.get(i).EPC  +
//		                    " | Antenna : " +MyTags.get(i).Antenna +
//		                    " | TID:"+ (MyTags.get(i).TID) +
//		                    " | RSSI : "+Integer.valueOf(MyTags.get(i).RSSI));
		            
		            ArrTagID.add(MyTags.get(i).EPC);
		        }
				
//				mượn sách
				if(kthd==0) {														//kiểm tra hóa đơn đã mượn chưa
					for (int i = 0; i < ArrTagID.size(); i++) {
						if(ArrIDBook.size() != 0) {
							for (int j = 0; j < ArrIDBook.size(); j++) {
									if(ArrIDBook.get(j).equals(ArrTagID.get(i))) {
										//duyệt mảng book
										for (int k = 0; k < listBook.size(); k++) {
											if(ArrIDBook.get(j).equals(listBook.get(k).book_id)) {
												System.out.println(ArrIDBook.get(j)+"| Bạn đã mượn thành công cuốn sách "+ listBook.get(k).book_title + "| sách đã hủy kích hoạt");
												Vector row = new Vector();
										        row.add(ArrIDBook.get(j));
										        row.add(listBook.get(k).book_title);	
										        row.add("sách đã hủy kích hoạt");
										        model.addRow(row);
												//book.dataUpdateSL(listBook.get(k), 0);
											}								
										}
										ArrIDBook.remove(ArrIDBook.get(j));
										j--;
										ArrTagID.remove(ArrTagID.get(i));
										i--;
										break;
									}
							}
						}//else {
							
						//}
					}
					
					//tách ra từ else
					if(ArrTagID.size() != 0) {											// thêm
						for (int l = 0; l < ArrTagID.size(); l++) {						//thêm
							for (int k = 0; k < listBook.size(); k++) {
								if(ArrTagID.get(l).equals(listBook.get(k).book_id)) {
										System.out.println(ArrTagID.get(l)+"| cuốn sách " + listBook.get(k).book_title + " chưa được đăng kí | bạn không thể mang ra thư viện");
										Vector row = new Vector();
								        row.add(ArrTagID.get(l));
								        row.add(listBook.get(k).book_title);	
								        row.add("chưa được đăng kí");
								        model.addRow(row);
										flag = 0;
								}
							}
						}//thêm
					}//thêm
					
					
					if(ArrIDBook.size() != 0) {
						for (int i = 0; i < ArrIDBook.size(); i++)
						{
							for (int k = 0; k < listBook.size(); k++) {
								if(ArrIDBook.get(i).equals(listBook.get(k).book_id)) {
									System.out.println(ArrIDBook.get(i)+"| bạn chưa lấy cuốn sách "+ listBook.get(k).book_title);
									Vector row = new Vector();
							        row.add(ArrIDBook.get(i));
							        row.add(listBook.get(k).book_title);	
							        row.add("chưa lấy");
							        model.addRow(row);
								}
							}
						}
					}else {
						if(flag == 1) {
							for(int i = 0; i < listBorrow.size(); i++) {
								if(listBorrow.get(i).borrow_id.equals(a)) {
									borrow.dataUpdateSL(listBorrow.get(i), 1);			//cật nhật trạng thái mượn
									System.out.println("hóa đơn của bạn đã được duyệt");					
									thanhcong.setText("Mã mượn được duyệt");								
								}
							}
						}
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		}
		
	}
	
	public static void main(String[] args) {
		giaodienmuonsach f= new giaodienmuonsach();
		
	}
	

}
