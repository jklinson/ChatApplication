//
//  ViewController.h
//  CabotChatApp
//
//  Created by cabot on 21/08/14.
//  Copyright (c) 2014 cabot. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "JSQMessages.h"
#import "SocketIO.h"
#import "SocketIOPacket.h"

@interface ViewController : JSQMessagesViewController<SocketIODelegate>

@property (nonatomic,strong) SocketIO* socketIO;
@property (strong, nonatomic) NSMutableArray *messages;
@property (copy, nonatomic) NSDictionary *avatars;

@property (strong, nonatomic) UIImageView *outgoingBubbleImageView;
@property (strong, nonatomic) UIImageView *incomingBubbleImageView;

@end
