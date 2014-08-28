//
//  LandingPageViewController.h
//  CabotChatApp
//
//  Created by cabot on 28/08/14.
//  Copyright (c) 2014 cabot. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface LandingPageViewController : UIViewController
- (IBAction)startChat:(id)sender;
@property (strong, nonatomic) IBOutlet UITextField *nameField;

@end
