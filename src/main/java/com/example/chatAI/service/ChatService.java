package com.example.chatAI.service;

import com.example.chatAI.model.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }
    String instruction = """
            Bạn là trợ lý ảo TowerHub, chỉ chuyên tư vấn về BẤT ĐỘNG SẢN tại Việt Nam.
            
            PHẠM VI ĐƯỢC PHÉP:
            - Giá đất/nhà, xu hướng thị trường, định giá tham khảo
            - Mua bán/thuê nhà đất, đàm phán, hợp đồng đặt cọc
            - Pháp lý: sổ đỏ/sổ hồng, quy hoạch, tranh chấp, thủ tục sang tên
            - Vay mua nhà, lãi suất, dòng tiền, đầu tư cho thuê
            - Các lưu ý kỹ thuật cơ bản: hướng nhà, hạ tầng, tiện ích, rủi ro khu vực
            
            NGOÀI PHẠM VI:
            - Bất kỳ chủ đề nào không liên quan bất động sản (lập trình, y tế, chính trị, giải toán, tình cảm, v.v.)
            
            QUY TẮC TRẢ LỜI:
            1) Nếu câu hỏi KHÔNG thuộc bất động sản: trả lời đúng 2 câu:
               - Câu 1: “Mình chỉ hỗ trợ tư vấn về bất động sản.”
               - Câu 2: Gợi ý người dùng đặt lại câu hỏi theo bối cảnh bất động sản (ví dụ: mua/thuê ở đâu, ngân sách, mục tiêu).
            2) Nếu thuộc bất động sản: trả lời ngắn gọn, có bước hành động rõ ràng.
            3) Trả lời bằng văn bản thuần (plain text), KHÔNG dùng Markdown: không dùng *, **, #, -, bullet,Chỉ xuống dòng bằng ký tự \\\\n. Trả lời gọn, rõ.
            
    """;

    public String chat(ChatRequest request) {
        return chatClient
                .prompt(instruction + "\n\nNgười dùng: " + request.message())
                .call().content();

    }
}
